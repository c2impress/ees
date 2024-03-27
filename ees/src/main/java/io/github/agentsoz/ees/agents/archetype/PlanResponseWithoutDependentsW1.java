package io.github.agentsoz.ees.agents.archetype;

/*-
 * #%L
 * Emergency Evacuation Simulator
 * %%
 * Copyright (C) 2014 - 2024 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import io.github.agentsoz.bdiabm.data.ActionContent;
import io.github.agentsoz.bdiabm.v3.AgentNotFoundException;
import io.github.agentsoz.ees.Constants;
import io.github.agentsoz.jill.lang.*;
import io.github.agentsoz.util.Location;

import java.util.Map;


@PlanInfo(postsGoals = {
		"io.github.agentsoz.ees.agents.archetype.GoalGotoW1",
})
public class PlanResponseWithoutDependentsW1 extends Plan {

	ArchetypeAgentW1 agent = null;
	Location xyHome;

	public PlanResponseWithoutDependentsW1(Agent agent, Goal goal, String name) {
		super(agent, goal, name);
		this.agent = (ArchetypeAgentW1)agent;
		body = steps;
	}

	/**
	 * Applies during initial response, when there are no dependents to attend to and agent is not at home
	 * @return true if the agent has no dependents, false otherwise
	 */
	public boolean context() {
		boolean applicable = false;
		setName(this.getClass().getSimpleName()); // give this plan a user friendly name for logging purposes
		boolean isStuck = Boolean.valueOf(agent.getBelief(ArchetypeAgentW1.State.isStuck.name()));
		if (isStuck) {
			return false;
		}
		boolean hasDependents = Boolean.valueOf(agent.getBelief(ArchetypeAgentW1.Beliefname.HasDependents.name()));
		if (!hasDependents) {
			xyHome = agent.getHomeLocation();
			try {
				double distHome = agent.getDrivingDistanceTo(xyHome);
				applicable = distHome > 0;
			}  catch (AgentNotFoundException e) {
				agent.handleAgentNotFoundException(e.getMessage());
			}
		}
		agent.out("thinks " + getFullName() + " is " + (applicable ? "" : "not ") + "applicable");
		return applicable;
	}

	PlanStep[] steps = {
			() -> {
				agent.out("will do #" + getFullName());

				boolean willGoHomeBeforeLeaving = Boolean.valueOf(agent.getBelief(ArchetypeAgentW1.Beliefname.WillGoHomeBeforeLeaving.name()));
				if (willGoHomeBeforeLeaving) {
					// Go home
					agent.out("will go home to " + xyHome + " #" + getFullName());
					subgoal(new GoalGotoW1(GoalGotoW1.class.getSimpleName(),
							xyHome,
							Constants.EvacActivity.Home,
							Double.valueOf(agent.getBelief(ArchetypeAgentW1.Beliefname.LagTimeInMinsForInitialResponse.name()))));
					// subgoal should be last call in any plan step
				} else {
					// Continue doing what it is doing
					agent.out("will wait and see #" + getFullName());
					this.drop(); // all done, drop the remaining plan steps
				}
			},
			() -> {
				// Abort if we are stuck
				if(Boolean.valueOf(agent.getBelief(ArchetypeAgentW1.State.isStuck.name()))) {
					drop();
					return;
				}
				// Check if we have arrived
				if (ActionContent.State.PASSED.equals(agent.getLastBdiActionState())) {
					agent.out("reached home at " + xyHome + " #" + getFullName());
				} else {
				    try {
						Location[] xy = ((Location[]) agent.getQueryPerceptInterface().queryPercept(
								String.valueOf(agent.getId()), Constants.REQUEST_LOCATION, null));
						agent.out("is stuck between locations " + xy[0] + " and " + xy[1] + " #" + getFullName());
					}  catch (AgentNotFoundException e) {
						agent.handleAgentNotFoundException(e.getMessage());
					}
				}
			},
	};

	@Override
	public void setPlanVariables(Map<String, Object> vars) {
	}
}

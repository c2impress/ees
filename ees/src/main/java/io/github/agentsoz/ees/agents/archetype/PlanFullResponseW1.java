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

import io.github.agentsoz.jill.lang.*;

import java.util.Map;

@PlanInfo(postsGoals = {
		"io.github.agentsoz.ees.agents.archetype.GoalInitialResponseW1",
		"io.github.agentsoz.ees.agents.archetype.GoalFinalResponseW1",
})
public class PlanFullResponseW1 extends Plan {

	ArchetypeAgentW1 agent = null;

	public PlanFullResponseW1(Agent agent, Goal goal, String name) {
		super(agent, goal, name);
		this.agent = (ArchetypeAgentW1)agent;
		body = steps;
	}

	public boolean context() {
		setName(this.getClass().getSimpleName()); // give this plan a user friendly name for logging purposes
		boolean isStuck = Boolean.valueOf(agent.getBelief(ArchetypeAgentW1.State.isStuck.name()));
		boolean applicable = !isStuck;
		agent.out("thinks " + getFullName() + " is " + (applicable ? "" : "not ") + "applicable");
		return applicable;
	}

	PlanStep[] steps = {
			() -> {
				agent.out("will do #" + getFullName());
				// Do initial response
				subgoal(new GoalInitialResponseW1(GoalInitialResponseW1.class.getSimpleName()));
				// subgoal should be last call in any plan step
			},
			() -> {
				// Then do final response
				subgoal(new GoalFinalResponseW1(GoalFinalResponseW1.class.getSimpleName()));
				// subgoal should be last call in any plan step
			},
	};

	@Override
	public void setPlanVariables(Map<String, Object> vars) {
	}
}
